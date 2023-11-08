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
      id: 0,
      imgSrc:
        'https://i.pinimg.com/736x/64/fb/63/64fb63cf7acbad70b9ece908b5b1b351.jpg',
      title: 'Inglês',
      newCardsNumber: 10,
      learnCardsNumber: 20,
      reviewCardsNumber: 5,
    },
    {
      id: 1,
      imgSrc:
        'https://i.pinimg.com/736x/1d/9d/66/1d9d66fb397de98f3c85b3f3f372e662.jpg',
      title: 'Espanhol',
      newCardsNumber: 5,
      learnCardsNumber: 15,
      reviewCardsNumber: 7,
    },
    {
      id: 2,
      imgSrc:
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJPk7D7Oc6a_pmJ7F9587ePMSMpJfVBMWcfYDm4CQuZdmwUJX77tXCrbaSzzkbZBiL7hk&usqp=CAU',
      title: 'Francês',
      newCardsNumber: 8,
      learnCardsNumber: 12,
      reviewCardsNumber: 3,
    },
  ])
})

app.get('/friends', (req, res) => {
  res.send([
    {
      id: 0,
      name: 'João',
      username: 'joaozin_god',
      imgSrc:
        'https://i.pinimg.com/736x/64/fb/63/64fb63cf7acbad70b9ece908b5b1b351.jpg',
    },
    {
      id: 0,
      name: 'Gabriel',
      username: 'gabriel2xd',
      imgSrc:
        'https://i.pinimg.com/736x/1d/9d/66/1d9d66fb397de98f3c85b3f3f372e662.jpg',
    },
    {
      id: 0,
      name: 'Lucas',
      username: 'lukinhas2022',
      imgSrc:
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJPk7D7Oc6a_pmJ7F9587ePMSMpJfVBMWcfYDm4CQuZdmwUJX77tXCrbaSzzkbZBiL7hk&usqp=CAU',
    },
  ])
})


app.get('/Notification', (req, res) => {
  res.send([
    {notificationText: 'Ronnie Coleman',
    imgSrc:'https://upload.wikimedia.org/wikipedia/commons/c/c9/RONNIE_COLEMAN.jpg'},
    {notificationText: 'Phil Heath',
    imgSrc:'http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcQByt7RsuwZOqhe9t1ArYD5UCmrqkHa8Tadj0UlqRVQwUnWOCxehO7wNCP01dPYlAn8'},
    {notificationText: 'Dorian Yates',
    imgSrc:'https://m.media-amazon.com/images/M/MV5BYjVhMThhMWUtYTU3OS00YzBiLWI2NzgtOTg1MDI4YzY0MmEzXkEyXkFqcGdeQXVyNjUxMjc1OTM@._V1_FMjpg_UY367_.jpg 319w, https://m.media-amazon.com/images/M/MV5BYjVhMThhMWUtYTU3OS00YzBiLWI2NzgtOTg1MDI4YzY0MmEzXkEyXkFqcGdeQXVyNjUxMjc1OTM@._V1_FMjpg_UY551_.jpg 479w, https://m.media-amazon.com/images/M/MV5BYjVhMThhMWUtYTU3OS00YzBiLWI2NzgtOTg1MDI4YzY0MmEzXkEyXkFqcGdeQXVyNjUxMjc1OTM@._V1_FMjpg_UY337_.jpg 293w, https://m.media-amazon.com/images/M/MV5BYjVhMThhMWUtYTU3OS00YzBiLWI2NzgtOTg1MDI4YzY0MmEzXkEyXkFqcGdeQXVyNjUxMjc1OTM@._V1_FMjpg_UY576_.jpg 501w, https://m.media-amazon.com/images/M/MV5BYjVhMThhMWUtYTU3OS00YzBiLWI2NzgtOTg1MDI4YzY0MmEzXkEyXkFqcGdeQXVyNjUxMjc1OTM@._V1_FMjpg_UX1100_.jpg 1100w, https://m.media-amazon.com/images/M/MV5BYjVhMThhMWUtYTU3OS00YzBiLWI2NzgtOTg1MDI4YzY0MmEzXkEyXkFqcGdeQXVyNjUxMjc1OTM@._V1_FMjpg_UY1264_.jpg'},
    {notificationText: 'Dexter Jackson',
    imgSrc:'http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcSsGflqHrL6ACz3zh62fzibpnr6nUQHXTfWrSyxYGOTodox5sAC_8MLZFVL8iW0Em_e'},
    {notificationText: 'Jay Cutler',
    imgSrc:'http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcTl1mL0LZxWdyDuZHTX70dmMvE9ppKJXcvHjZdNqkPiD_IcXvRoLYzaPG1uyKGtvjn4'},
    
  ])
})



app.listen(3000, () => {
  console.log('Server is running!')
})
