export interface DomainUser {
  name: string;
  avatarUrl: string;
  username: string;
  accessToken: string;
  refreshToken?: string;
  expiresIn?: number | null;
}
