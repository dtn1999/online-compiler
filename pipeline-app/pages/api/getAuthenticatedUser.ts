import axios from "axios";
import { getSession } from "next-auth/client";
import { getToken } from "next-auth/jwt";

const secret = process.env.SECRET;
let accessToken;

export default async function getAutenticatedUser(req: any, res: any) {
  const session = await getSession({ req });
  console.log(req, session);
  if (!session) {
    return res.status(401).end();
  }

  const token = await getToken({ req,encryption: false });

  res.status(200).json(token);
}
