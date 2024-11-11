package lian.sample.repository.order.entity

import jakarta.persistence.*
import kr.co.mustit.jpa.NullToZeroDateConverter
import lian.sample.jpa.BooleanToYNConverter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Entity
@Table(name = "CATALOG")
class OrderJpaEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATALOG_NO", nullable = false)
  val catalogNo: Long = 0,

  @Column(name = "CATEGORY_NO")
  var categoryNo: Long?,

  @Convert(converter = BooleanToYNConverter::class)
  @Column(name = "DELETE_YN", nullable = false)
  var isDelete: Boolean = false,

  @Convert(converter = BooleanToYNConverter::class)
  @Column(name = "USE_YN", nullable = false)
  var isUse: Boolean = true,

  @CreatedDate
  @Convert(converter = NullToZeroDateConverter::class)
  @Column(name = "INS_DTIME", nullable = false, updatable = false)
  val insertDate: Instant,

  @LastModifiedDate
  @Convert(converter = NullToZeroDateConverter::class)
  @Column(name = "UPD_DTIME")
  var updateDate: Instant,

  @LastModifiedBy
  @Column(name = "UPD_OPRT", nullable = false)
  var updateOperator: String
  ) {

  fun updateIsDelete() {
    this.isDelete = true
  }

  fun updateCategory(categoryNo: Long?) {
    this.categoryNo = categoryNo
  }

  fun updateIsUse(isUse: Boolean, updateOperator: String) {
    this.isUse = isUse
    this.updateOperator = updateOperator
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as OrderJpaEntity

    if (catalogNo != other.catalogNo) return false
    if (categoryNo != other.categoryNo) return false
    if (isDelete != other.isDelete) return false
    if (isUse != other.isUse) return false
    if (insertDate != other.insertDate) return false
    if (updateDate != other.updateDate) return false
    if (updateOperator != other.updateOperator) return false

    return true
  }

  override fun hashCode(): Int {
    var result = catalogNo.hashCode()
    result = 31 * result + categoryNo.hashCode()
    result = 31 * result + isDelete.hashCode()
    result = 31 * result + isUse.hashCode()
    result = 31 * result + insertDate.hashCode()
    result = 31 * result + updateDate.hashCode()
    result = 31 * result + (updateOperator?.hashCode() ?: 0)
    return result
  }
}
