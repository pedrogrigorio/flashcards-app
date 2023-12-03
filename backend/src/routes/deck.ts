import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import DeckController from '../controllers/DeckController'

const deckRoutes = Router()
// test (add AuthMiddleware)
deckRoutes.post('/deck/create', AuthMiddleware, DeckController.createDeck)
deckRoutes.put(
  '/deck/:id/updateDeck',
  AuthMiddleware,
  DeckController.updateDeck,
)
deckRoutes.post(
  '/deck/:id/deleteDeck',
  AuthMiddleware,
  DeckController.deleteDeck,
)
deckRoutes.get('/deck/getAllDeck', AuthMiddleware, DeckController.getAllDeck)
deckRoutes.post(
  '/deck/:id/getDeckById',
  AuthMiddleware,
  DeckController.getDeckById,
)

export default deckRoutes
