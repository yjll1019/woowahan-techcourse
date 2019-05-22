# java-coordinate
좌표계산기 미션을 진행하기 위한 저장소

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

### 좌표계산기
#### todo
1. 입력
+ 한 개, 두 개, 세 개 또는 네 개의 좌표를 입력받는다.
    + [예외] 범위를 벗어난 경우에는 다시 입력을 받는다.
    + [예외] 각 점은 중복되지 않아야 한다.
    + [예외] 사각형은 x축과 y축에 평행해야 한다.
    + [예외] 삼각형은 세 점이 한 직선 위에 있어선 안된다.
+ 좌표값과 좌표값 사이를 '-' 문자로 구분한다.

2. 출력
+ 입력된 좌표를 화면에 출력한다.
+ 입력된 좌표가 2개인 경우에 두 점 사이의 거리를 출력한다.
+ 입력된 좌표가 3개인 경우에 삼각형의 넓이를 출력한다.
+ 입력된 좌표가 4개인 경우에 사각형의 넓이를 출력한다.
