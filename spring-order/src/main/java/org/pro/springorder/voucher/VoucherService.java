package org.pro.springorder.voucher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class VoucherService {

    private VoucherRepository voucherRepository;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public Voucher getVoucher(UUID voucherId) {
        return voucherRepository
                .findById(voucherId)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Can not find a voucher for {0}", voucherId)));
    }

    public void useVoucher(Voucher voucher) {

    }
}
