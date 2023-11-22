import { Router } from 'express'
import helloRoutes from './hello'
import userRoutes from './user'
import authRoutes from './auth'

const routes = Router()

routes.use(helloRoutes)
routes.use(userRoutes)
routes.use(authRoutes)

export default routes
