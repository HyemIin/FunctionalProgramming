package exercizes.chap2

typealias FUN<A, B> = (A) -> B

// 확장 함수
infix fun <A, B, C> FUN<A, B>.andThen(other: FUN<B, C>): FUN<A, C>
        = { a: A -> other(this(a)) }

// andThen 함수의 역할은 두 함수를 연결(composition).
// this(a) : 현재 함수(this)를 실행하여 B 값을 얻음.
// other(this(a)) : 위에서 얻은 B 값을 other 함수에 전달하여 C 값을 얻음.
// 최종적으로 (A) -> C 형태의 새로운 함수를 반환.