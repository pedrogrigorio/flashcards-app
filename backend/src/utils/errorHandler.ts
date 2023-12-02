import { Response } from 'express'
import { z } from 'zod'

function handleError(res: Response, error: Error | z.ZodError | unknown) {
  if (error instanceof z.ZodError) {
    return res.status(400).json({ error: 'Invalid data' })
  } else if (error instanceof Error) {
    return res.status(400).json({ error: error.message })
  }

  return res.status(500).json({ error: 'Internal Server Error' })
}

export { handleError }
