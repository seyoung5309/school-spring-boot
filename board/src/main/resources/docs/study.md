# REST API와 관련된 어노테이션은 무엇일까?

## @RestController

## @RequestMapping("/")

## @GetMapping

## @GetMapping("/")

## @PostMapping

---

# 어떻게 주고 어떻게 받을까?

## @PathVariable
- url path 안에 있는 값을 변수로 읽기
  - posts/**1**

## @RequestParam
- 쿼리 파라미터에 있는 값을 변수로 읽기 
  - posts?keyword=스프링
  - keyword는 메서드 안에서 받는 파라미터와 이름이 같아야 한다.

## @RequestBody
- request body에 있는 값 여러 개(json) 읽기
```json
  {"title" : "a" , "content" : "b"}
```

---

# REST API

여러 원칙 중 대표적인 2가지 

## URI는 자원을 명사로 표현한다. 
  - 무엇을 할 지는 URI에 포함하지 않는다. (명사형)


## 자원에 대한 행위는 HTTP Method로 표현한다. 
  - 무엇을 할 지는 HTTP Method로 표현한다. 

---

## HTTP Method
- GET, POST, PUT, DELETE, (PATCH)

# 200 OK 
- 201 Created
- 204 No Content 예) 게시글 삭제 

# 스프링이 없으면 겪는 불편함
- 순수 java로 HTTP 서버 만들기 
- 컨트롤러에 멤버 변수 추가
  - 메일/sms 알림 
  - 강결합 <- 객체 지향에서는 지양해야 하는 방법

---

# 의존성
한 객체가 동작하기 위해서 다른 객체가 필요한 경우 의존겅이이 있다고 한다.
PostController는 Notifier에 의존한다.

# IoC
Inversion of Control = 제어의 역전
PostController가
- 내가 쓸 객체를 내자 정하고, 내가 만든다
-> 결정과 생성을 외부(스프링)로 넘김
스프링은
- @Component 어노테이션으로 미리 알려주지 않으면 모른다고 하고 서버 실행을 포기한다. 

# DI
- 의존성 주입 (Dependency Injection)
- @Component라는 어노테이션을 붙임으로써 스프링이 EmailNotifier 객체도 만들고 PostController 객체도 만들었다.
  - PostController를 만들 때 필요한 Notifier 객체도 알아서 넣어줬다. (생성자)
- 이 행동 자체를 필요한 의존성을 외부에서 넣어줬기 때문에 의존성을 외부에서 넣어줬다고 말한다. 

# 정리
- 의존성: 한 객체가 동작하기 위해 필요로 하는 다른 객체 
- IOC: 제어의 역전. 의존성을 만들고 결정하는 주체가 객체 자신에서 외부로 바뀌는 것 
- DI: 필요한 의존성을 외부에서 만들어서 넣어주는 것. IOC를 구현하는 대표적인 방법
- 생성자 주입: 의존성 주입의 방식 중 하나. 생성자를 통해서 의존성을 주입하는 것 

---

우리가 한 것: PostController를 생성자 주입으로 바꾼 것
우리가 알고 있는 것: EmailNotifier를 스프링이 알아서 만들었다. 
우리가 아직 모르는 것: 
1) 대체 언제 만들었지?
2) 대체 어디에 보관해 뒀던 거지? 

# 지금까지 배운 스프링 어플리케이션의 흐름
1. 우리가 코드를 작성하면서 클래스에 @Component 어노테이션을 붙인다. 
2. 애플리케이션이 실행될 때 스프링이 @Component 어노테이션이 붙은 클래스를 찾는다. 
3. 다 찾아서 객체로 만든다. 
4. 만든 객체를 컨테이너에 보관한다. 
   - 이 컨테이너를 스프링 컨테이너(ApplicationContext)라고 한다. 
5. 다른 곳에서 객체가 필요하면 스프링이 컨테이너에서 꺼내서 전달해준다. 