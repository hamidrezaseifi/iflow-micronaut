package com.pth.gui.controllers;

import com.pth.gui.authentication.GuiAuthenticationProvider;
import com.pth.gui.controllers.helper.AuthenticatedController;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.exception.GuiNotAuthorizedException;
import com.pth.gui.helpers.SessionDataHelper;
import com.pth.gui.models.User;
import com.pth.gui.models.UserDashboardMenu;
import com.pth.gui.models.gui.FileSavingData;
import com.pth.gui.models.gui.GuiSocketMessage;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.models.workflow.base.WorkflowBased;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.services.*;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;
import net.bytebuddy.implementation.bytecode.Throw;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/archive/data")
public class ArchiveController extends AuthenticatedController {

    private final IUserHandler userHandler;
    private final IUploadFileManager uploadFileManager;

    public ArchiveController(IUserHandler userHandler,
                             IUploadFileManager uploadFileManager) {
        this.uploadFileManager = uploadFileManager;
        this.userHandler = userHandler;
    }

    @Post("/uploadtempfile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public HttpResponse<GuiSocketMessage> uploadTempFile(@Parameter CompletedFileUpload file, Session session) {
        //CompletedFileUpload file

        final GuiSocketMessage results = GuiSocketMessage.generate("processing");

        if (file != null && file.getFilename().isEmpty() == false) {
            String tempPath = null;
            try {
                tempPath = this.uploadFileManager.saveSingleMultipartInTemp(file);
            } catch (IOException e) {
                throw new GuiCustomizedException(String.format("Error in saving upload file! %s", e.getMessage()) );
            }

            final FileSavingData fileSaveData = FileSavingData.generateFromFilePath(tempPath);
            fileSaveData.getFileExtention();

            final File tempFile = new File(tempPath);
            String hocrpPath = tempFile.getParent() + "/" + tempFile.getName() + ".hocr";
            hocrpPath = hocrpPath.replace("\\", "/");

            results.setStatus("done");
            results.setFileName(file.getFilename());
            results.setFileNotHash(tempPath);
            results.setHocrFileNotHash(hocrpPath);
            results.setIsFileImage(fileSaveData.isFileImage());
            results.setIsFilePdf(fileSaveData.isFilePdf());

        }

        return HttpResponse.created(results);

    }

    @Error(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = GuiCustomizedException.class)
    public HttpResponse notFound(HttpRequest request, GuiCustomizedException ex) {

        JsonError error = new JsonError(ex.getMessage())
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>serverError()
                .body(error);
    }


}
