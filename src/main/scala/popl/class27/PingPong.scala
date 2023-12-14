package popl.class27

import akka.actor.{Actor, ActorRef, Props}
import akka.actor.ActorSystem
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._

enum Message:
  case Ping(x: Int)
  case Pong(count: Int)
  case Stop

import Message._

class PingActor(pong: ActorRef) extends Actor:
  pong ! Ping(0)
  
  def receive =
    case Pong(c) if c > 4 =>
      sender() ! Stop
      println("ping stopped")
      context.stop(self)
    case Pong(c) =>
      println("ping")
      sender() ! Ping(c + 1)
 
class PongActor extends Actor:
  def receive =
    case Ping(c) =>
      println("pong")
      sender() ! Pong(c + 1)
    case Stop =>
      println("pong stopped")
      context.system.terminate()
 
object PingPong: 
  def main(args: Array[String]) =
    val system = ActorSystem("PingPong")
    val pong = system.actorOf(Props[PongActor]())
    val ping = system.actorOf(Props(new PingActor(pong)))
    //Await.result(system.whenTerminated, Duration.Inf)
