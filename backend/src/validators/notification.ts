import { z } from 'zod'

export const sendFriendRequestSchema = z.object({
  senderId: z.number(),
  receiverId: z.number(),
})
