import { Router } from 'express'
import HelloController from '../controllers/HelloController'

// define user routes

const helloRoutes = Router()

helloRoutes.get('/hello', HelloController.helloWorld)

export default helloRoutes
