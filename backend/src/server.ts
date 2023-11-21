import express from 'express'
import cors from 'cors'
import routes from './routes/routes'

const app = express()

app.use(cors())

// configura rotas definidas no arquivo routes.ts
app.use(routes)

app.listen(3000, () => {
  console.log('Server is running!')
})
