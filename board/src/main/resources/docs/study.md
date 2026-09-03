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
  - 무엇을 할 지는 URI에 포함하지 않는다.


## 자원에 대한 행위는 HTTP Method로 표현한다. 
  - 무엇을 할 지는 HTTP Method로 표현한다. 
