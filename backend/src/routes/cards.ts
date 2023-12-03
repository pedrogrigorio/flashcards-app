import { Router } from 'express'
import CardController from '../controllers/CardController'

const cardRoutes = Router()

cardRoutes.post('/card/create', CardController.createCard)
cardRoutes.put('/card/updateCard', CardController.updateCard)
cardRoutes.post('/card/deleteCard', CardController.deleteCard)
cardRoutes.post('/card/getAllCards', CardController.getAllCards)
cardRoutes.post('/card/getCardById', CardController.getCardById)
cardRoutes.post('/card/getCardsForToday', CardController.getCardsForToday)
cardRoutes.post('/card/UpdateCardsReview', CardController.getCardById)
