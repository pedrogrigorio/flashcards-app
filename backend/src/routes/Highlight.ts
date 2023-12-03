import { Router } from 'express'
import HighLightController from '../controllers/HighLightController'

const highlightRoutes = Router()

highlightRoutes.post(
  '/highlight/createHighlight',
  HighLightController.createHighlight,
)
highlightRoutes.post(
  '/highlight/updateHighlight',
  HighLightController.updateHighlight,
)
highlightRoutes.post(
  '/highlight/getAlHighlight',
  HighLightController.getAllHighlights,
)
