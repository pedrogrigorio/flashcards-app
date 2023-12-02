import { NextFunction, Request, Response } from 'express'

export function CheckUserOwnership(
  req: Request,
  res: Response,
  next: NextFunction,
) {
  const userIdFromToken = parseInt(req.userId)
  const userIdFromRoute = parseInt(req.params.id)

  if (userIdFromToken !== userIdFromRoute) {
    return res.status(403).json({
      error:
        'You do not have permission to perform this action on another user profile',
    })
  }

  next()
}
