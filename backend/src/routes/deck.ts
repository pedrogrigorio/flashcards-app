import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import DeckController from '../controllers/DeckController'
import upload from '../lib/multer'

const deckRoutes = Router()
// test (add AuthMiddleware) Is necessary fixing problem with default add in data base (prisma). When the imgSrc is in default the update dosen't works
deckRoutes.get('/deck/create', AuthMiddleware, DeckController.createDeck)
deckRoutes.put(
  '/decK/updateDeck',
  AuthMiddleware,
  upload.single('file'),
  DeckController.updateDeck,
)
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
