import HighlightRepository from '../repositories/HighlightRepository'

class HighLightService {
  async createHighlight(
    CardId: number,
    color: string,
    startPosition: number,
    endPosition: number,
  ) {
    const newHighlight = await HighlightRepository.createHighlight(
      CardId,
      color,
      startPosition,
      endPosition,
    )

    return newHighlight
  }

  async updateHighlight(
    highlightId: number,
    color: string,
    startPosition: number,
    endPosition: number,
  ) {
    const update = await HighlightRepository.updateHighlight(
      highlightId,
      color,
      startPosition,
      endPosition,
    )

    return update
  }

  async getAllHighlights(cardId: number) {
    const allHighlights = await HighlightRepository.getAllHighlights(cardId)

    return allHighlights
  }
}

export default new HighLightService()
