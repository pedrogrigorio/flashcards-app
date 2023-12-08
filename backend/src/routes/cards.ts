import { Router } from 'express'
import CardController from '../controllers/CardController'
import { AuthMiddleware } from '../middlewares/auth'

const cardRoutes = Router()

cardRoutes.post('/card/create', AuthMiddleware, CardController.createCard)
cardRoutes.put('/card/updateCard', AuthMiddleware, CardController.updateCard)
cardRoutes.post('/card/deleteCard', AuthMiddleware, CardController.deleteCard)
cardRoutes.post(
  '/card/:id/getAllCards',
  AuthMiddleware,
  CardController.getAllCards,
)
cardRoutes.post(
  '/card/:id/getCardById',
  AuthMiddleware,
  CardController.getCardById,
)
cardRoutes.post(
  '/card/getCardsForToday',
  AuthMiddleware,
  CardController.getCardsForToday,
)
cardRoutes.post(
  '/card/UpdateCardsReview',
  AuthMiddleware,
  CardController.updateCardsReviewed,
)

export default cardRoutes
