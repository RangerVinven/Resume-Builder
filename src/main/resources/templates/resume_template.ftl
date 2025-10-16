<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>${resume.person_information.name} – Resume</title>

        <style>
/* --- PAGE LAYOUT --- */
@page {
    size: A4;
}

    body {
        font-family: "Helvetica", "Arial", sans-serif;
        font-size: 12pt;
        line-height: 1.5;
        color: #222;
    }

    .page {
        box-sizing: border-box;
        margin: 0 auto;
    }

    .page:not(:last-child) {
        page-break-after: always;
    }

    /* --- HEADER --- */
    header {
        display: flex;
        align-items: flex-start;
        justify-content: flex-start;
        width: 100%;
        flex-direction: column;
        margin-bottom: 1.5rem;
        text-align: center;
    }

    header h1 {
        margin: 0 auto;
        font-size: 24pt;
    }

    header p {
        margin: 0 0;
        color: #555;
    }

    /* --- SECTION --- */
    section {
        margin-top: -0.5rem;
        margin-bottom: 1.5rem;
    }

    section h2, .personal-statement h2 {
        border-bottom: 2px solid #444;
        padding-bottom: 0.1rem;
        margin-bottom: 0.8rem;
        text-transform: uppercase;
        font-size: 13pt;
        letter-spacing: 1px;
    }

    /* --- SECTION ITEM --- */
    .section-item {
        margin-bottom: -1rem;
    }

    .section-item-header {
        display: flex;
        justify-content: space-between;
        align-items: baseline;
        flex-wrap: nowrap;
        gap: 0;
        margin: -0.65rem 0 0 0;
        padding: 0;
        width: 100%;
    }

    .section-item-header h3 {
        margin: 0;
        padding: 0;
        display: inline-block;
        font-size: 11pt;
        font-weight: bold;
        flex: 0;
        width: 45%;
    }

    .section-item-header span.meta {
        font-size: 10pt;
        color: #666;
        flex: 0;
        white-space: nowrap;
        display: inline-block;
        margin-left: auto;
        text-align: right;
        width: 54%;
        padding: 0;
    }

    .section-item p.subtitle {
        margin: -0.25rem 0 0 0;
        font-size: 10.5pt;
        color: #555;
    }

    .personal-statement p {
        margin: -0.5rem 0 1.25rem 0;
        line-height: 1.1;
        font-size: 10.5pt;
    }

    .section-item ul {
        margin: 0.025rem 0 0 0;
    }

    .section-item ul li {
        line-height: 1.1;
        font-size: 10.5pt;
    }

    .section-item:has(p.subtitle) ul li:first-child {
        margin-top: -0.65rem;
    }

    .section-item ul li:last-child {
        margin-bottom: 1.25rem;
    }

    .section-item p.subtitle.no-bullets {
        margin-bottom: 0.65rem;
    }
        </style>
    </head>

    <body>
        <div class="page">
            <!-- ===== HEADER ===== -->
            <header>
                <h1>${resume.person_information.name}</h1>

                <p>${resume.person_information.details?join(' | ')}</p>
            </header>

            <#if resume.personal_statement?? && resume.personal_statement?has_content>
            <div class="section-item personal-statement">
                <div class="section-item-header">
                    <#if resume.personal_statement.name?? && resume.personal_statement.name?has_content>
                    <h2>${resume.personal_statement.name}</h2>
                    </#if>
                </div>

                <#if resume.personal_statement.statement?? && resume.personal_statement.statement?has_content>
                <p>${resume.personal_statement.statement}</p>
                </#if>
            </div>
            </#if>

            <!-- ===== SECTIONS ===== -->
            <#list resume.sections as section>
            <section>
                <h2>${section.name}</h2>

                <#list section.items as item>
                <div class="section-item">
                    <div class="section-item-header">
                        <h3>${item.title}</h3>

                        <#if item.meta?? && item.meta?has_content>
                        <span class="meta">${item.meta}</span>
                        </#if>
                    </div>

                    <#if item.subtitle?? && item.subtitle?has_content>
                    <p class="subtitle<#if !(item.bullet_points?? && item.bullet_points?has_content)> no-bullets</#if>">${item.subtitle}</p>
                    </#if>

                    <ul>
                    <#if item.bullet_points?? && item.bullet_points?has_content>
                        <#list item.bullet_points as point>
                            <li>${point}</li>
                        </#list>
                    </#if>
                    </ul>
                </div>
                </#list>
            </section>
            </#list>
        </div>
    </body>
</html>
