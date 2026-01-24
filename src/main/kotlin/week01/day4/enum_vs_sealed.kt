package week01.day4

// NEW, PAID, FAILED

// enum variant
enum class PaymentStatusEnum {
    NEW,
    PAID,
    FAILED;

    fun checkPay(): Boolean {
        return this == PaymentStatusEnum.PAID
    }
}

// sealed variant
sealed class PaymentStatusSealed {
    object New: PaymentStatusSealed()
    data class Paid(val total: Double): PaymentStatusSealed()
    data class Failed(val message: String): PaymentStatusSealed()
}

fun checkSealedPay(statusSealed: PaymentStatusSealed): String {
    return when(statusSealed) {
        PaymentStatusSealed.New -> "..."
        is PaymentStatusSealed.Paid -> "Paid: ${statusSealed.total}"
        is PaymentStatusSealed.Failed -> "Failed: ${statusSealed.message}"
    }
}

fun main() {
    // enum
    val appleStatus = PaymentStatusEnum.NEW
    val bananStatus = PaymentStatusEnum.PAID
    val cucumberStatus = PaymentStatusEnum.FAILED
    println("Apple: ${appleStatus.checkPay()}")
    println("Banan: ${bananStatus.checkPay()}")
    println("Cucumber: ${cucumberStatus.checkPay()}")
    println()

    // sealed
    var wallet = 50.0
    val tomato = 10.0

    val status = if(wallet >= tomato) {
        wallet -= tomato
        PaymentStatusSealed.Paid(tomato)
    } else PaymentStatusSealed.Failed("No money")

    println(status)
}