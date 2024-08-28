DROP SCHEMA IF EXISTS scheduler;
create schema scheduler;
use scheduler;

CREATE TABLE `ServiceProvider` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `service_id` bigint NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `is_active` boolean NOT NULL DEFAULT true,
  `created_at` datetime NOT NULL DEFAULT (NOW()),
  `updated_at` datetime NOT NULL DEFAULT (NOW())
);

CREATE TABLE `Service` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `created_at` datetime NOT NULL DEFAULT (NOW()),
  `updated_at` datetime NOT NULL DEFAULT (NOW())
);

CREATE TABLE `Slot` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `frequency` bigint,
  `frequency_unit_id` bigint,
  `created_at` datetime NOT NULL DEFAULT (NOW()),
  `updated_at` datetime NOT NULL DEFAULT (NOW())
);

CREATE TABLE `SlotFrequencyType` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `value` varchar(255),
  `created_at` datetime NOT NULL DEFAULT (NOW()),
  `updated_at` datetime NOT NULL DEFAULT (NOW())
);

CREATE TABLE `Status` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `value` varchar(255),
  `created_at` datetime NOT NULL DEFAULT (NOW()),
  `updated_at` datetime NOT NULL DEFAULT (NOW())
);

CREATE TABLE `Calendar` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `service_provider_id` bigint,
  `slot_id` bigint NOT NULL,
  `start_datetime` datetime NOT NULL,
  `user_id` bigint NOT NULL,
  `status_id` bigint NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (NOW()),
  `updated_at` datetime NOT NULL DEFAULT (NOW())
);

CREATE INDEX `IX_Operator_service_id` ON `ServiceProvider` (`service_id`);

ALTER TABLE `ServiceProvider` ADD CONSTRAINT `FK_Operator_service_id` FOREIGN KEY (`service_id`) REFERENCES `Service` (`id`);

ALTER TABLE `Slot` ADD CONSTRAINT `FK_Slot_frequency_unit` FOREIGN KEY (`frequency_unit_id`) REFERENCES `SlotFrequencyType` (`id`);

ALTER TABLE `Calendar` ADD CONSTRAINT `FK_Calendar_service_provider_id` FOREIGN KEY (`service_provider_id`) REFERENCES `ServiceProvider` (`id`);

ALTER TABLE `Calendar` ADD CONSTRAINT `FK_Calendar_slot_id` FOREIGN KEY (`slot_id`) REFERENCES `Slot` (`id`);


INSERT INTO `scheduler`.`Service` ( `name`) VALUES ( 'CAR_SERVICE');
INSERT INTO `scheduler`.`ServiceProvider` ( `name`, `email`, `service_id`, `mobile_number`, `is_active`) VALUES ('provider 1', 'provider1@smthing.com', '1', '324353542', '1');
INSERT INTO `scheduler`.`ServiceProvider` (`name`, `email`, `service_id`, `mobile_number`, `is_active`) VALUES ('provider 2', 'provider2@smthing.com', '1', '324353542', '1');
INSERT INTO `scheduler`.`ServiceProvider` ( `name`, `email`, `service_id`, `mobile_number`, `is_active`) VALUES ( 'provider 3', 'provider3@smthing.com', '1', '324353542', '1');
INSERT INTO `scheduler`.`SlotFrequencyType` ( `value`) VALUES ('MINUTE');
INSERT INTO `scheduler`.`Slot` ( `frequency`, `frequency_unit_id`) VALUES ( '60', '1');
INSERT INTO `scheduler`.`Status` (`value`) VALUES ( 'BOOKED');
INSERT INTO `scheduler`.`Status` (`value`) VALUES ( 'CANCELED');
