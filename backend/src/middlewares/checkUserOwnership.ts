import { NextFunction, Request, Response } from 'express'

export function CheckUserOwnership(
  req: Request,
  res: Response,
  next: NextFunction,
) {
  const userIdFromToken = req.userId
  const userIdFromRoute = req.params.id

  if (userIdFromToken !== userIdFromRoute) {
    return res.status(403).json({ error: 'Unauthorized access.' })
  }

  next()
}
