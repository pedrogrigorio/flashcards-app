import { z } from 'zod'

export const sendFriendRequestSchema = z.object({
  receiverId: z.number(),
})
