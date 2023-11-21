import { Router } from 'express'
import helloRoutes from './hello'

// junta todas as rotas em um só Router

const routes = Router()

routes.use(helloRoutes)

export default routes
