import { Router } from 'express'
import CardController from '../controllers/CardController'

const cardRoutes = Router()

cardRoutes.post('/card/:id/create', CardController.createCard)
cardRoutes.put('/card/:id/updateCard', CardController.updateCard)
cardRoutes.post('/card/:id/deleteCard', CardController.deleteCard)
cardRoutes.post('/card/:id/getAllCards', CardController.getAllCards)
cardRoutes.post('/card/:id/getCardById', CardController.getCardById)
cardRoutes.post('/card/:id/getCardsForToday', CardController.getCardsForToday)
cardRoutes.post('/card/UpdateCardsReview', CardController.updateCardsReviewed)

export default cardRoutes
