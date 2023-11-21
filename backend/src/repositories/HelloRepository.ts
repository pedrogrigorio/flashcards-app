// Acesso aos dados (buscas no Prisma)

class HelloRepository {
  async helloWorld() {
    // Esse código é só uma simulação de uma promise
    const promise = new Promise((resolve) => {
      resolve([{ hello: 'world' }])
    })

    const data = await promise
    return data
  }
}

export default new HelloRepository()
