import { Router } from 'express'
import AuthController from '../controllers/AuthController'

const authRoutes = Router()

authRoutes.post('/auth', AuthController.login)

export default authRoutes
