interface ReturnNexReview {
  newStartEasy: number
  newDate: Date
  newSuccessfulReview: Date | null
}

class SpacedRepetitionSystem {
  private defaultIntervalModifier: number
  private hardInterval: number
  private defaultEasyBonus: number
  private standartCurrentInterval: number

  constructor() {
    this.defaultIntervalModifier = 1
    this.hardInterval = 1.2
    this.defaultEasyBonus = 1.3
    this.standartCurrentInterval = 1
  }

  private HardComputeNextReview(
    lastSuccessfulReview: Date | null,
    startEasy: number,
  ): ReturnNexReview {
    const hard = new Date()

    if (lastSuccessfulReview == null) {
      const days = Math.floor(
        this.standartCurrentInterval *
          this.hardInterval *
          this.defaultIntervalModifier,
      )
      hard.setDate(hard.getDate() + days)
    } else {
      const currentInterval = Math.floor(
        (hard.getTime() - lastSuccessfulReview.getTime()) /
          (1000 * 60 * 60 * 24),
      )

      const days = Math.floor(
        currentInterval * this.hardInterval * this.defaultIntervalModifier,
      )
      hard.setDate(hard.getDate() + days)
    }

    const newNextReview: ReturnNexReview = {
      newStartEasy: (startEasy -= 15),
      newDate: hard,
      newSuccessfulReview: lastSuccessfulReview,
    }

    return newNextReview
  }

  private GoodComputeNextReview(
    lastSuccessfulReview: Date | null,
    startEasy: number,
  ): ReturnNexReview {
    const good = new Date()

    if (lastSuccessfulReview == null) {
      const days = Math.floor(
        this.standartCurrentInterval * startEasy * this.defaultIntervalModifier,
      )
      good.setDate(good.getDate() + days)
    } else {
      const currentInterval = Math.floor(
        (good.getTime() - lastSuccessfulReview.getTime()) /
          (1000 * 60 * 60 * 24),
      )

      const days = Math.floor(
        currentInterval * startEasy * this.defaultIntervalModifier,
      )
      good.setDate(good.getDate() + days)
    }

    lastSuccessfulReview = new Date()
    lastSuccessfulReview.setDate(lastSuccessfulReview.getDate())

    const newNextReview: ReturnNexReview = {
      newStartEasy: startEasy,
      newDate: good,
      newSuccessfulReview: lastSuccessfulReview,
    }

    return newNextReview
  }

  private easyComputeNextReview(
    lastSuccessfulReview: Date | null,
    startEasy: number,
  ): ReturnNexReview {
    const easy = new Date()

    if (lastSuccessfulReview == null) {
      const days = Math.floor(
        this.standartCurrentInterval *
          startEasy *
          this.defaultIntervalModifier *
          this.defaultEasyBonus,
      )
      easy.setDate(easy.getDate() + days)
    } else {
      const currentInterval = Math.floor(
        (easy.getTime() - lastSuccessfulReview.getTime()) /
          (1000 * 60 * 60 * 24),
      )

      const days = Math.floor(
        currentInterval *
          startEasy *
          this.defaultIntervalModifier *
          this.defaultEasyBonus,
      )
      easy.setDate(easy.getDate() + days)
    }

    lastSuccessfulReview = new Date()
    lastSuccessfulReview.setDate(lastSuccessfulReview.getDate())

    const newNextReview: ReturnNexReview = {
      newStartEasy: (startEasy += 15),
      newDate: easy,
      newSuccessfulReview: lastSuccessfulReview,
    }

    return newNextReview
  }

  nextReview(
    lastSuccessfulReview: Date | null,
    stampLevel: number | null,
    startEasy: number,
  ): ReturnNexReview {
    switch (stampLevel) {
      case 1:
        return this.HardComputeNextReview(lastSuccessfulReview, startEasy)

      case 2:
        return this.GoodComputeNextReview(lastSuccessfulReview, startEasy)

      case 3:
        return this.easyComputeNextReview(lastSuccessfulReview, startEasy)

      default:
        throw new Error('Unexpected stampLevel: ${stampLevel')
    }
  }
}

export default new SpacedRepetitionSystem()
