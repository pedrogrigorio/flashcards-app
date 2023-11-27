import 'dotenv/config'

import express from 'express'
import cors from 'cors'
import routes from './routes/routes'
import path from 'path'

const app = express()

app.use(cors())
app.use(express.json())
app.use(routes)

app.use('/image', express.static(path.join(__dirname, '../uploads')))

app.listen(3000, () => {
  console.log('Server is running!')
})
