package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class MaintenanceMiddleware implements IRequestHandler {
    private IRequestHandler handler;
    private ServiceUnavailableResult serviceUnavailableResult;

    public MaintenanceMiddleware(IRequestHandler handler, OffsetDateTime startDateTime) {
        this.handler = handler;
        OffsetDateTime endDateTime = startDateTime.plusHours(1);
        this.serviceUnavailableResult = new ServiceUnavailableResult(startDateTime, endDateTime);
    }

    @Override
    public ResultBase handle(Request request) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime startTime = this.serviceUnavailableResult.getStartDateTime();
        OffsetDateTime endTime = this.serviceUnavailableResult.getEndDateTime();

        if (startTime.isBefore(now) && endTime.isAfter(now)) {
            return this.serviceUnavailableResult;
        }

        return this.handler.handle(request);
    }
}
