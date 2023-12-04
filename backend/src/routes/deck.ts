import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import DeckController from '../controllers/DeckController'

const deckRoutes = Router()
// test (add AuthMiddleware)
deckRoutes.get('/deck/create', AuthMiddleware, DeckController.createDeck)
deckRoutes.put('/decK/updateDeck', AuthMiddleware, DeckController.updateDeck)
deckRoutes.delete(
  '/deck/:deckId/deleteDeck',
  AuthMiddleware,
  DeckController.deleteDeck,
)
deckRoutes.get('/deck/getAllDeck', AuthMiddleware, DeckController.getAllDeck)
deckRoutes.get(
  '/deck/:id/getDeckById',
  AuthMiddleware,
  DeckController.getDeckById,
)

export default deckRoutes
