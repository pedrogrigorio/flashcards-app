import { z } from 'zod'

export const registerSchema = z.object({
  username: z.string(),
  email: z.string().email(),
  password: z.string(),
  passwordConfirmation: z.string(),
})

export const updateProfileSchema = z.object({
  name: z
    .string()
    .min(2, { message: 'Name must contain at least 2 character(s).' }),
})

export const updateStatsSchema = z.object({
  cardsReviewed: z.number(),
})

export const searchUsersSchema = z.object({
  query: z.string(),
})
