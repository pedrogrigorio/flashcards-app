import multer from 'multer'
import path from 'path'

const storage = multer.diskStorage({
  destination: (req, file, callback) => {
    console.log('oi')
    callback(null, 'uploads/')
  },
  filename: (req, file, callback) => {
    callback(null, Date.now() + path.extname(file.originalname))
  },
})

const upload = multer({ storage })

export default upload
