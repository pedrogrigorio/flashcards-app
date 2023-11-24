import { Request, Response } from 'express'

class FriendController {
  async addFriend(req: Request, res: Response) {
    return res.status(400).json({ to_do: 'TO DO' })
  }

  async acceptFriendRequest(req: Request, res: Response) {
    return res.status(400).json({ to_do: 'TO DO' })
  }

  async deleteFriend(req: Request, res: Response) {
    return res.status(400).json({ to_do: 'TO DO' })
  }

  async getAllFriends(req: Request, res: Response) {
    return res.status(400).json({ to_do: 'TO DO' })
  }

  async getFriendById(req: Request, res: Response) {
    return res.status(400).json({ to_do: 'TO DO' })
  }
}

export default new FriendController()
