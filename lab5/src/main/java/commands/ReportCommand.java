package commands;

import exceptions.DesktopOpenException;
import exceptions.FileWriterException;
import exceptions.InvalidCommandException;
import exceptions.InvalidTemplateException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.Catalog;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand extends Command{
    public void applyCommand(Catalog catalog, Configuration configuration) throws InvalidCommandException {
        super.setConfiguration(configuration);
        try {
            this.applyCommand(catalog);
        } catch (IOException exception) {
            InvalidCommandException invalidCommandException = new InvalidCommandException(exception);
            throw invalidCommandException;
        }
    }

    @Override
    public void applyCommand(Catalog catalog) throws InvalidCommandException, InvalidTemplateException, FileWriterException, DesktopOpenException {
        Map root = new HashMap();
        root.put("catalog", catalog);
        catalog.setUrl("something.html");

        Template template = null;
        try {
            template = super.getConfiguration().getTemplate("test.ftlh");
        } catch (IOException exception) {
            InvalidTemplateException invalidTemplateException = new InvalidTemplateException(exception);
            throw invalidTemplateException;
        }

        Writer writer = null;
        try {
            writer = new FileWriter(catalog.getUrl());
        } catch (IOException exception) {
            FileWriterException fileWriterException = new FileWriterException(exception);
            throw fileWriterException;
        }
        try {
            template.process(root, writer);
        } catch (IOException | TemplateException exception) {
            InvalidTemplateException invalidTemplateException = new InvalidTemplateException(exception);
            throw invalidTemplateException;
        }

        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(catalog.getUrl()));
        } catch (IOException exception) {
            DesktopOpenException desktopOpenException = new DesktopOpenException(exception);
            throw desktopOpenException;
        }

        System.out.println("\n--report done!--");
    }
}
