import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import DeckController from '../controllers/DeckController'

const deckRoutes = Router()
// test (add AuthMiddleware)
deckRoutes.post('/deck/create', DeckController.createDeck)
deckRoutes.put('/deck/:id/updateDeck', DeckController.updateDeck)
deckRoutes.post('/deck/:id/deleteDeck', DeckController.deleteDeck)
deckRoutes.post('/deck/:id/getAllDeck', DeckController.getAllDeck)
deckRoutes.post('/deck/:id/getDeckById', DeckController.getDeckById)

export default deckRoutes
