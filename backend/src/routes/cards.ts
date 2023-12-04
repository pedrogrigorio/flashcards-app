import { Router } from 'express'
import CardController from '../controllers/CardController'
import { AuthMiddleware } from '../middlewares/auth'

const cardRoutes = Router()

cardRoutes.post('/card/create', AuthMiddleware, CardController.createCard)
cardRoutes.put('/card/updateCard', CardController.updateCard)
cardRoutes.post('/card/deleteCard', AuthMiddleware, CardController.deleteCard)
cardRoutes.post('/card/:id/getAllCards', CardController.getAllCards)
cardRoutes.post('/card/:id/getCardById', CardController.getCardById)
cardRoutes.post('/card/getCardsForToday', CardController.getCardsForToday)
cardRoutes.post('/card/UpdateCardsReview', CardController.updateCardsReviewed)

export default cardRoutes
