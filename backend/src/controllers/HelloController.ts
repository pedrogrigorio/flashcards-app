import { Request, Response } from 'express'
import HelloService from '../services/HelloService'

// Trata a requisição (recebe e retorna os dados)

class HelloController {
  async helloWorld(req: Request, res: Response) {
    const data = await HelloService.helloWorld()
    res.json(data)
  }
}

export default new HelloController()
