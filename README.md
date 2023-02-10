# Produtor-Consumidor com Threads

Este é um algoritmo de exemplo que ilustra o problema do produtor-consumidor usando threads em Java. Ele inclui a implementação de uma classe `ProdutorConsumidorThread` que pode ser usada para criar várias threads que trabalham juntas para produzir e consumir itens de uma fila de bloqueio.
Como funciona

O algoritmo cria uma fila de bloqueio e um semáforo para controlar o acesso à fila. Cada thread é construída com um ID, o semáforo e a fila de bloqueio como parâmetros. A thread então entra em um loop infinito enquanto a bandeira de parada não estiver definida. Dentro do loop, a thread chama o método `produzir()` para adicionar um item à fila e, em seguida, adquire o semáforo antes de chamar o método `consumir()` para retirar um item da fila. Finalmente, o semáforo é liberado.

## Como usar
Para usar este algoritmo, você precisará criar uma instância da classe `ProdutorConsumidorThread` e chamar o método `start()` para iniciar a execução da thread. Você também pode chamar o método `stopThread()` para definir a bandeira de parada e encerrar a execução da thread.

Abaixo está um exemplo de como usar o algoritmo em um método main:
```java
public static void main(String[] args) {
        int numThreads = 100; 
        int filaSize = 100;
        int semaphorePermits = filaSize / 2; 

        Semaphore semaphore = new Semaphore(semaphorePermits);
        Semaphore semaphore2 = new Semaphore(semaphorePermits); 
        BlockingQueue<Integer> fila = new ArrayBlockingQueue<Integer>(filaSize);

        ProdutorConsumidorThread[] threads = new ProdutorConsumidorThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new ProdutorConsumidorThread(i, semaphore,semaphore2, fila);
            threads[i].start();
        }

        for(int i = 0; i < numThreads; i++){
            threads[i].stopThread();
        }
    }
``` 
Este código cria um semáforo e uma fila de bloqueio, em seguida, cria um vetor de objetos `ProdutorConsumidorThread` e inicia cada um em uma thread separada. O número de threads criadas é determinado pela variável `numThreads`.


## Autor
<div align="left" text="blue">
  <b> Matt Balda </b>
</div>
