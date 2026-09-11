## 입문 주차 프로젝트
<br>
<br>
<br>
과제 설명 :<br>
https://psj45633.tistory.com/145<br>
https://psj45633.tistory.com/146<br>
<br>
트러블슈팅 :<br>
https://psj45633.tistory.com/147<br>
<br>
<br>
<br>
<br>
<br>
1\. Controller, Service, Repository는 각각 어떤 역할을 맡나요?<br>
→<br>
Controller - 일반 사용자가 애플리케이션과 상호작용하는 사용자 인터페이스 및 커뮤니케이션 계층<br>
Service - 비즈니스 로직을 처리하는 역할. 우리가 생각하는 밴엔드의 모든 핵심 로직이 운영되는 계층<br>
Repository - 데이터베이스와의 상호작용을 담당. 실제 데이터베이스에서 데이터를 저장하거나 가져오는 계층<br>
<br>
<br>
<br>
2. @Service를 붙이지 않으면 서버가 뜨지 않는 이유는 무엇인가요?<br>
→<br>
@Service는 비즈니스 로직을 담당하는 클래스를 Spring Bean으로 등록하여 Spring Container가 생성 및 관리하고<br>
의존성 주입할 수 있도록 하는 어노테이션이다.<br>
<br>
<br>
<br>
3. @Transactional(readOnly = true)는 무슨 뜻이며, 저장하는 메서드에 붙이면 왜 안 되나요?<br>
→<br>
@Transactional(readOnly = true)는 읽기 전용으로서 저장하는 메서드인 CUD 작업이 불가<br>
<br>
<br>
<br>
4. @NotBlank, @NotNull, @NotEmpty는 각각 어떤 값을 걸러내나요?<br>
→<br>
@NotNull - null<br>
@NotEmpty - null, 비어있음("")<br>
@NotBlank - null, 비어있음(""), 공백(" ")<br>
<br>
<br>
<br>
5. 엔티티를 그대로 응답하지 않고 DTO로 바꿔서 응답하는 이유는 무엇인가요?<br>
→<br>
Entity를 직접 노출하지 않고 DTO를 사용해서 DB모델과 API 응답 모델을 분리하고,<br>
필요한 데이트만 안전하게 클라이언트에게 전달하기 위해<br>
<br>
<br>
<br>
6. 이름 변경에서 save()를 호출하지 않았는데 DB에 반영되는 이유는 무엇인가요?<br>
→<br>
findById()로 조회한 game은 @Transactional 안에서 JPA가 관리하는 영속상태이고,<br>
game.rename("");으로 값을 변경하면 JPA가 처음 조회했을 때의 값과 현재 값을 비교해서 변경됐다는걸 감지함<br>
(Dirty Checking)<br>
<br>
<br>
<br>
7. Bean Validation이 필요한 이유<br>
→<br>
클라이언트가 보낸 잘못된 값을 Service의 비즈니스 로직에 들어가기 전에 검증해서 막기 위함<br>
DTO에 검증 조건을 걸어두면 Controller에서 @Valid가 검증을 실행해서 조건에 맞지 않는 요청은<br>
400 Bad Request로 처리할 수 있음
