import HelloRepository from '../repositories/HelloRepository'

// Regras de negócio (lógica)

class HelloService {
  async helloWorld() {
    const users = await HelloRepository.helloWorld()

    return users
  }
}
export default new HelloService()
