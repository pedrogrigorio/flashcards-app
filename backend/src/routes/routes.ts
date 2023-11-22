import { Router } from 'express'
import helloRoutes from './hello'
import userRoutes from './user'

const routes = Router()

routes.use(helloRoutes)
routes.use(userRoutes)

export default routes
