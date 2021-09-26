import NextAuth, { User } from "next-auth";
import Providers from "next-auth/providers";
import { isAssetError } from "next/dist/client/route-loader";
import { DomainUser } from "./type";

export default NextAuth({
  providers: [
    Providers.GitHub({
      clientId: process.env.GITHUH_CLIENTID,
      clientSecret: process.env.GITHUH_CLIENT_SECRET,
      scope: ["public_repo", "write:repo_hook", "read:user", "user:email"],
      accessTokenUrl: "https://github.com/login/oauth/access_token",
      authorizationUrl: "https://github.com/login/oauth/authorize",
      profileUrl: "https://api.github.com/user",
      profile(profile) {
        return {
          id: profile.id,
          name: profile.name || profile.login,
          email: profile.email,
          image: profile.avatar_url,
        };
      },
    }),
  ],
  callbacks: {
    jwt(token, user, account, profile, isNewUser) {
      if (profile && account?.accessToken) {
        token.accessToken = account.accessToken;
        const { login: username, avatar_url: avatarUrl, name } = profile as any;
        const { accessToken, refreshToken, expires_in: expiresIn } = account;
        const authenticatedUser: DomainUser = {
          name,
          username,
          avatarUrl,
          accessToken,
          expiresIn,
          refreshToken,
        };
        // make a REST call to the gateway service to register the user if not present or login
        token.authenticatedUser = authenticatedUser;
      }
      return token;
    },
  },
});
