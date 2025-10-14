<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>${resume.person_information.name} – Resume</title>

  <style>
    /* --- PAGE LAYOUT --- */
    @page {
      size: A4;
      margin: 2cm;
    }

    body {
      font-family: "Helvetica", "Arial", sans-serif;
      font-size: 12pt;
      line-height: 1.5;
      color: #222;
      background: #f8f8f8;
      display: flex;
      justify-content: center;
      padding: 2rem 0;
    }

    .page {
      background: white;
      width: 210mm;
      min-height: 297mm;
      padding: 2cm;
      box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
      box-sizing: border-box;
      page-break-after: always;
    }

    /* --- HEADER --- */
    header {
      text-align: center;
      margin-bottom: 1.5rem;
    }

    header h1 {
      margin: 0;
      font-size: 24pt;
    }

    header p {
      margin: 0.25rem 0;
      color: #555;
    }

    /* --- SECTION --- */
    section {
      margin-bottom: 1.5rem;
    }

    section h2 {
      border-bottom: 2px solid #444;
      padding-bottom: 0.2rem;
      margin-bottom: 0.8rem;
      text-transform: uppercase;
      font-size: 14pt;
      letter-spacing: 1px;
    }

    /* --- SECTION ITEM --- */
    .section-item {
      margin-bottom: 0.8rem;
    }

    .section-item-header {
      display: flex;
      justify-content: space-between;
      align-items: baseline;
    }

    .section-item-header h3 {
      margin: 0;
      font-size: 12pt;
      font-weight: bold;
    }

    .section-item-header span.meta {
      font-size: 10pt;
      color: #666;
    }

    .section-item p.subtitle {
      margin: 0.2rem 0;
      font-style: italic;
      color: #555;
    }

    .section-item ul {
      margin: 0.3rem 0 0 1.2rem;
    }

    .section-item ul li {
      margin-bottom: 0.2rem;
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
              <p class="subtitle">${item.subtitle}</p>
            </#if>

            <ul>
              <#list item.bullet_points as point>
                <li>${point}</li>
              </#list>
            </ul>
          </div>
        </#list>
      </section>
    </#list>
  </div>
</body>
</html>

