package com.compose.babyai.data.model

//MyOrderModels
// MyOrderModels.kt


/*
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

// Data Models
@Parcelize
data class OrderItem(
    val id: String,
    val name: String,
    val color: String,
    val size: String,
    val price: Double,
    val originalPrice: Double? = null,
    val imageUrl: Int? = null
) : Parcelable

@Parcelize
data class PriceBreakdown(
    val amount: Double,
    val shipping: Double,
    val tax: Double,
    val total: Double
) : Parcelable

@Parcelize
data class DeliveryDetails(
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String,
    val phone: String
) : Parcelable

@Parcelize
data class TrackingInfo(
    val trackingNumber: String,
    val estimatedDelivery: Date
) : Parcelable

@Parcelize
data class PaymentInfo(
    val method: String,
    val lastFourDigits: String
) : Parcelable

enum class OrderStatus {
    ORDER_PLACED,
    PROCESSING,
    SHIPPED,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}

@Parcelize
data class TrackingEvent(
    val status: OrderStatus,
    val title: String,
    val location: String,
    val description: String,
    val timestamp: Date
) : Parcelable

@Parcelize
data class OrderSummary(
    val orderId: String,
    val orderDate: Date,
    val items: List<OrderItem>,
    val priceBreakdown: PriceBreakdown,
    val deliveryDetails: DeliveryDetails,
    val trackingInfo: TrackingInfo,
    val paymentInfo: PaymentInfo,
    val trackingHistory: List<TrackingEvent>,
    val orderStatus: OrderStatus // नया field जोड़ें
) : Parcelable*/
