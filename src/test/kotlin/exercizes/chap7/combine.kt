package exercizes.chap7

import sun.awt.image.MultiResolutionCachedImage.map

fun combine(other: Holder<T>, f: (T, T) -> T): Holder<T> =
    map { x -> f(x, other.value) }