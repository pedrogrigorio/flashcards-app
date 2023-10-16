import express from 'express'
import cors from 'cors'

const app = express()

app.use(cors())

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.get('/decks', (req, res) => {
  res.send([
    {
      imgSrc:
        'https://i.pinimg.com/736x/64/fb/63/64fb63cf7acbad70b9ece908b5b1b351.jpg',
      title: 'Inglês',
      newCardsNumber: 10,
      learnCardsNumber: 20,
      reviewCardsNumber: 5,
    },
    {
      imgSrc:
        'https://i.pinimg.com/736x/1d/9d/66/1d9d66fb397de98f3c85b3f3f372e662.jpg',
      title: 'Espanhol',
      newCardsNumber: 5,
      learnCardsNumber: 15,
      reviewCardsNumber: 7,
    },
    {
      imgSrc:
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJPk7D7Oc6a_pmJ7F9587ePMSMpJfVBMWcfYDm4CQuZdmwUJX77tXCrbaSzzkbZBiL7hk&usqp=CAU',
      title: 'Francês',
      newCardsNumber: 8,
      learnCardsNumber: 12,
      reviewCardsNumber: 3,
    },
  ])
})

app.listen(3000, () => {
  console.log('Server is running!')
})
