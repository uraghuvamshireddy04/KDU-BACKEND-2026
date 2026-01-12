package com.example.quickship.service;

import com.example.quickship.models.DeliveryType;
import com.example.quickship.models.PackageStatus;
import com.example.quickship.repository.PacakgeRepository;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.quickship.models.deliverypackage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PackageService {
    private static final Logger log = LoggerFactory.getLogger(PackageService.class);

    private final PacakgeRepository repo;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public PackageService(PacakgeRepository repo) {
        this.repo = repo;
    }

    public deliverypackage addPackageAsync(String destination, double weight, DeliveryType deliveryType) {
        deliverypackage delivery = new deliverypackage(destination, weight, PackageStatus.PENDING, deliveryType);
        repo.save(delivery);

        executor.submit(() -> addSorted(delivery.getId()));

        return delivery;
    }


    private void addSorted(String id) {
        try {

            Thread.sleep(3000);

            repo.findById(id).ifPresent(b -> {
                b.setStatus(PackageStatus.SORTED);
                repo.save(b);
            });

            log.info("Sorting finished={} status=SORTED", id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("sorting interrupted Id={}", id);
        } catch (Exception e) {
            log.error("Sorting failed Id={}", id, e);
        }
    }

    public List<deliverypackage> getAll() {
        return repo.findAll();
    }

    public double totalRevenue() {
        return repo.findAll().stream()
                .filter(delivery -> delivery.getStatus().name().equals("SORTED"))
                .mapToDouble(deliverypackage::getWeight)
                .map(weight -> weight*(2.5))
                .sum();

    }


    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}
