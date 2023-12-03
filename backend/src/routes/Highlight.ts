import { Router } from 'express'
import HighLightController from '../controllers/HighLightController'

const highlightRoutes = Router()

highlightRoutes.post(
  '/highlight/createHighlight',
  HighLightController.createHighlight,
)
highlightRoutes.post(
  '/highlight/:id/updateHighlight',
  HighLightController.updateHighlight,
)
highlightRoutes.post(
  '/highlight/:id/getAllHighlight',
  HighLightController.getAllHighlights,
)

export default highlightRoutes
