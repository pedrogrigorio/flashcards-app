import { Router } from 'express'
import UserController from '../controllers/UserController'

const userRoutes = Router()

userRoutes.post('/users/register', UserController.register)

export default userRoutes
