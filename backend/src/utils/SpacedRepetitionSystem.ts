interface ReturnNexReview {
  newStartEasy: number
  newDate: Date
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
    lastSuccessfulReview: Date,
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
    }

    return newNextReview
  }

  private GoodComputeNextReview(
    lastSuccessfulReview: Date,
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

    const newNextReview: ReturnNexReview = {
      newStartEasy: startEasy,
      newDate: good,
    }

    return newNextReview
  }

  private easyComputeNextReview(
    lastSuccessfulReview: Date,
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

    const newNextReview: ReturnNexReview = {
      newStartEasy: (startEasy += 15),
      newDate: easy,
    }

    return newNextReview
  }

  nextReview(
    lastSuccessfulReview: Date,
    nextReview: Date,
    stampLevel: number,
    startEasy: number,
  ) {
    switch (stampLevel) {
      case 1:
        nextReview.setDate(
          this.HardComputeNextReview(
            lastSuccessfulReview,
            startEasy,
          ).newDate.getDate(),
        )
        break

      case 2:
        nextReview.setDate(
          this.GoodComputeNextReview(
            lastSuccessfulReview,
            startEasy,
          ).newDate.getDate(),
        )
        break

      case 3:
        nextReview.setDate(
          this.easyComputeNextReview(
            lastSuccessfulReview,
            startEasy,
          ).newDate.getDate(),
        )
        break
    }
  }
}

export default new SpacedRepetitionSystem()
