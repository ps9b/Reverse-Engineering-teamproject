# Reverse-Engineering-teamproject

## [역할 A] 시스템 아키텍처 및 파일 I/O 분석 (2242958 박수빈)


담당 소스: Drawer.java, DrawerFrame.java

핵심 과제: 애플리케이션 전체의 진입점 구조를 파악하고, 상단 풀다운 메뉴(새 파일, 열기, 저장, 종료)의 이벤트 흐름을 도식화한다. 특히, ArrayList(Figure) 라는 다형성 컬렉션을 활용하여 그림 데이터를 통째로 파일에 저장하고 읽어오는 자바 객체 직렬화(Serialization) 메커니즘을 상세히 분석해야 한다.


## [역할 B] 엔진 및 컨트롤러 이벤트 흐름 분석 (2243232 김은서)

담당 소스: DrawerView.java

핵심 과제: 사용자 마우스 입력(MOUSE_PRESSED, mouseDragged, MOUSE_RELEASED)에 따라 화면의 상태(도형 생성 모드 vs 이동 모드)가 어떻게 제어되는지 제어 흐름도(Flowchart)를 작성한다. 마우스 좌표가 하위 도형 객체에 전달되어 동적으로 화면이 갱신되는 원리를 추적해야 한다.


## [역할 C] OOP 핵심 요소 및 도형 모델 구조 분석 (2204774 박소현)

담당 소스: Figure.java, OnePointFigure.java, TwoPointFigure.java, Point.java, Line.java, Box.java, Circle.java

핵심 과제: 추상 클래스(Figure)로부터 시작되는 상속 계층 구조를 분석하고 클래스 다이어그램으로 나타낸다. draw() 및 move() 메서드가 하위 도형들에서 어떻게 오버라이딩되어, 실행 시점에 동적 바인딩(Dynamic Binding)과 다형성을 발휘하는지 코드를 짚어가며 증명해야 한다.


## [역할 D] UX 요소 및 영역 판별/팝업 제어 분석 (2242936 김나영)

담당 소스: Popup.java, MainPopup.java, FigurePopup.java, TVPopup.java, TV.java

핵심 과제: 화면에서 마우스 우클릭을 했을 때, 마우스 좌표가 어떤 도형 위에 있는지 판별하는 영역 체크(Hit Testing) 수학적 알고리즘(Polygon _region, contains(), 기울기 연산 등)을 분석한다. 또한, 선택된 도형의 타입에 따라 서로 다른 팝업 메뉴가 동적으로 결합되는 구조를 분석해야 한다.
